package com.example.federico.mlibrefedericopuy

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.example.federico.mlibrefedericopuy.detail.ProductDetailRepository
import com.example.federico.mlibrefedericopuy.detail.ProductDetailViewModel
import com.example.federico.mlibrefedericopuy.model.Description
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class ProductDetailViewModelTest {

    lateinit var viewModel: ProductDetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var repository: ProductDetailRepository

    @Mock
    lateinit var description: Description

    @Mock
    lateinit var descriptionLiveData: MutableLiveData<Description>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `description is retrieved when viewModel is initialized`() {
        `when`(descriptionLiveData.value).thenReturn(description)
        `when`(repository.retrieveDescription(ArgumentMatchers.anyString())).thenReturn(descriptionLiveData)
        viewModel = ProductDetailViewModel(this.repository, ArgumentMatchers.anyString())

        verify(repository).retrieveDescription(ArgumentMatchers.anyString())
        assertEquals(description, viewModel.productDescription.value)
    }

    @Test
    fun `retrieveProductImages and retrieveDescription are called upon viewModel initialization with the correct productId`() {
        val productId = "123456"
        viewModel = ProductDetailViewModel(this.repository, productId)

        verify(repository).retrieveDescription(productId)
        verify(repository).retrieveProductImages(productId)
    }
}
