package com.dharyiswara.sehatqtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dharyiswara.sehatqtest.TestDataFactory
import com.dharyiswara.sehatqtest.helper.Resource
import com.dharyiswara.sehatqtest.helper.extension.clazz
import com.dharyiswara.sehatqtest.mock
import com.dharyiswara.sehatqtest.model.HomepageData
import com.dharyiswara.sehatqtest.repository.HomepageRepository
import com.dharyiswara.sehatqtest.ui.main.home.HomeViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class HomeViewModelTest {

    private val repository = mock(clazz<HomepageRepository>())
    private val viewModel = HomeViewModel(repository)


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getHomepage_withCall() {

        val req1 = false
        val req2 = true

        viewModel.homepageData.observeForever(mock())

        viewModel.getHomepage(req1)
        verify(repository).getHomepage(req1)

        reset(repository)

        viewModel.getHomepage(req2)
        verify(repository).getHomepage(req2)
    }

    @Test
    fun getHomepage_withResultUI() {

        val mock1 = MutableLiveData<Resource<List<HomepageData>>>()
        val mock2 = MutableLiveData<Resource<List<HomepageData>>>()

        val req1 = false
        val req2 = true

        `when`(repository.getHomepage(req1))
            .thenReturn(mock1)
        `when`(repository.getHomepage(req2))
            .thenReturn(mock2)

        val observer = mock<Observer<Resource<List<HomepageData>>>>()
        viewModel.homepageData.observeForever(observer)

        verify(observer, never()).onChanged(ArgumentMatchers.any())

        viewModel.getHomepage(req1)
        val value1 = Resource.success(TestDataFactory.makeHomepageData())
        mock1.value = value1
        verify(observer).onChanged(value1)

        reset(observer)

        viewModel.getHomepage(req2)
        val value2 = Resource.success(TestDataFactory.makeHomepageData())
        mock2.value = value2
        verify(observer).onChanged(value2)

    }

    @Test
    fun getHomepage_withNoRepositoryInteract() {

        viewModel.homepageData.observeForever(mock())
        verifyNoMoreInteractions(repository)

    }

}