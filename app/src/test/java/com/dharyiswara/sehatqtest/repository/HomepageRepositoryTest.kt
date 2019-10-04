package com.dharyiswara.sehatqtest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dharyiswara.sehatqtest.*
import com.dharyiswara.sehatqtest.database.dao.HomepageDao
import com.dharyiswara.sehatqtest.helper.AbsentLiveData
import com.dharyiswara.sehatqtest.helper.Resource
import com.dharyiswara.sehatqtest.helper.extension.clazz
import com.dharyiswara.sehatqtest.model.HomepageData
import com.dharyiswara.sehatqtest.network.NetworkService
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import retrofit2.Response

@RunWith(JUnit4::class)
class HomepageRepositoryTest {

    private val service = mock(clazz<NetworkService>())
    private val dao = mock(clazz<HomepageDao>())
    private val repository = HomepageRepository(service, dao, InstantAppExecutors())

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getHomepage_withResultFromNetwork() {
        val isReloadData = false
        val repositoryList = TestDataFactory.makeHomepageData()
        val resultNetwork = ApiUtil.createCall(Response.success(repositoryList))

        `when`(service.getHomePage())
            .thenReturn(resultNetwork)
        `when`(dao.getHomepage())
            .thenReturn(AbsentLiveData.create())

        val observer = mock<Observer<Resource<List<HomepageData>>>>()
        repository.getHomepage(isReloadData).observeForever(observer)

        verify(dao).getHomepage()
        verify(service).getHomePage()
    }

    @Test
    fun getHomepage_withResultFromLocal() {
        val isReloadData = false
        val repositoryList = TestDataFactory.makeHomepageData()
        val resultLocal = DaoUtil.createCall(repositoryList)

        `when`(service.getHomePage())
            .thenReturn(AbsentLiveData.create())
        `when`(dao.getHomepage())
            .thenReturn(resultLocal)

        val observer = mock<Observer<Resource<List<HomepageData>>>>()
        repository.getHomepage(isReloadData).observeForever(observer)

        verify(dao).getHomepage()
        verify(service, never()).getHomePage()
    }

    @Test
    fun getHomepage_withForceUpdate() {
        val isReloadData = true
        val repositoryList = TestDataFactory.makeHomepageData()
        val resultNetwork = ApiUtil.createCall(Response.success(repositoryList))
        val resultLocal = DaoUtil.createCall(repositoryList)

        `when`(service.getHomePage())
            .thenReturn(resultNetwork)
        `when`(dao.getHomepage())
            .thenReturn(resultLocal)

        val observer = mock<Observer<Resource<List<HomepageData>>>>()
        repository.getHomepage(isReloadData).observeForever(observer)

        verify(dao).getHomepage()
        verify(service).getHomePage()
    }

}