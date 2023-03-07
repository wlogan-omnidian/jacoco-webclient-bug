import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Test

class AsyncClassTest {

  @Test
  fun webWork() {
    MockWebServer().use {
      val classUnderTest = AsyncClass(it.url("testServer/").toString())
      it.enqueue(MockResponse().setBody("{}").setResponseCode(200))
      runBlocking {
        classUnderTest.doWebWork("foo")
      }
    }
  }

  @Test
  fun webWork2() {
    MockWebServer().use {
      val classUnderTest = AsyncClass(it.url("testServer/").toString())
      it.enqueue(MockResponse().setBody("{}").setResponseCode(200))
      runBlocking {
        classUnderTest.doWebWork2("foo")
      }
    }
  }

  @Test
  fun localWork() {
    val classUnderTest = AsyncClass("testServer/")
    runBlocking {
      classUnderTest.doLocalWork("bar")
    }
  }

}
