import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        // Extract 'jwt' cookie from Set-Cookie header
        val jwtCookie = originalResponse.headers("Set-Cookie")
            .find { it.startsWith("jwt=") }

        if (jwtCookie != null) {
            // Extract the value after '='
            val jwtValue = jwtCookie.substringAfter('=')

            // Now, you can use jwtValue as your JWT token
            Log.d("ReceivedCookiesInterceptor", "JWT token: $jwtValue")
        }

        return originalResponse
    }
}
