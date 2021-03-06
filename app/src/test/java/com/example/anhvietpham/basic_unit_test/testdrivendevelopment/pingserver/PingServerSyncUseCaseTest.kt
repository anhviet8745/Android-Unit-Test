package com.example.anhvietpham.basic_unit_test.testdrivendevelopment.pingserver

import com.example.anhvietpham.basic_unit_test.testdrivendevelopment.pingserver.network.PingServerHttpEndPointSync
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PingServerSyncUseCaseTest{
    private val mPingServerHttpEndPointSync: PingServerHttpEndPointSync = mock()
    private lateinit var SUT: PingServerSyncUseCase

    @Before
    fun setup(){
        SUT = PingServerSyncUseCase(mPingServerHttpEndPointSync)
        success()
    }

    @Test
    fun pingServerSync_success_successReturned() {
        // Arrange
        // Act
        val result = SUT.pingServerSync()
        // Assert
        assertThat(result,`is`(PingServerSyncUseCase.UseCaseResult.SUCCESS))
    }

    @Test
    fun pingServerSync_generalError_failureReturned() {
        // Arrange
        generalError()
        // Act
        val result = SUT.pingServerSync()
        // Assert
        assertThat(result,`is`(PingServerSyncUseCase.UseCaseResult.FAILURE))
    }

    @Test
    fun pingServerSync_networkError_failureReturned() {
        // Arrange
        networkError()
        // Act
        val result = SUT.pingServerSync()
        // Assert
        assertThat(result,`is`(PingServerSyncUseCase.UseCaseResult.FAILURE))
    }

    private fun networkError() {
        whenever(mPingServerHttpEndPointSync.pingServerSync()).thenReturn(PingServerHttpEndPointSync.EndPointResult.NETWORK_ERROR)
    }

    private fun generalError() {
        whenever(mPingServerHttpEndPointSync.pingServerSync()).thenReturn(PingServerHttpEndPointSync.EndPointResult.GENERAL_ERROR)
    }

    private fun success() {
        whenever(mPingServerHttpEndPointSync.pingServerSync()).thenReturn(PingServerHttpEndPointSync.EndPointResult.SUCCESS)
    }
}
