package com.example.federico.mlibrefedericopuy


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.federico.mlibrefedericopuy.list.ProductListRepository
import com.example.federico.mlibrefedericopuy.list.ProductListState
import com.example.federico.mlibrefedericopuy.list.ProductListViewModel
import com.example.federico.mlibrefedericopuy.model.ProductInfo
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class ProductListViewModelTest {

    lateinit var viewModel: ProductListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var repository: ProductListRepository

    @Mock
    lateinit var observer: Observer<ProductListState>

    @Mock
    lateinit var productLiveData: MutableLiveData<ProductListState>

    @Mock
    lateinit var productList: List<ProductInfo>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `searchProducts() updates queryLiveData with the correct value`() {
        val query = "query"
        viewModel = ProductListViewModel(repository)
        viewModel.searchProducts(query)

        assertEquals(query, viewModel.queryLiveData.value)
    }

    @Test
    fun `products are retrieved when queryLiveData is updated`() {
        val query = "query"
        viewModel = ProductListViewModel(repository)
        viewModel.results.observeForever(observer)
        `when`(productLiveData.value).thenReturn(ProductListState.ShowItems(productList))
        `when`(repository.retrieveProducts(query)).thenReturn(productLiveData)

        //Updates queryLiveData and triggers repository.retrieveProducts()
        viewModel.searchProducts(query)

        verify(repository, times(1)).retrieveProducts(query)
    }
}