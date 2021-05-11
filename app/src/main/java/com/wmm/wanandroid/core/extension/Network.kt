import androidx.annotation.StringRes
import com.google.gson.JsonSyntaxException
import com.wmm.wanandroid.R
import com.wmm.wanandroid.core.exception.Failure
import com.wmm.wanandroid.core.functional.Either
import com.wmm.wanandroid.core.platform.BaseBean
import org.json.JSONException
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException


suspend fun <T, R> request(
    call: Call<BaseBean<T>>,
    transform: (T) -> R,
    default: T
): Either<Failure, R> {
    return try {
        val response = call.awaitResponse()
        when (response.isSuccessful && response.body() != null && response.body()?.errorCode == 0) {
            true -> Either.Right(transform(response.body()?.data ?: default))
            false -> Either.Left(handleFailure(response))
        }
    } catch (exception: Throwable) {
        when (exception) {
            is JSONException -> Failure.JsonSyntaxError
            is JsonSyntaxException -> Failure.JsonSyntaxError
            is IllegalArgumentException -> Failure.JsonSyntaxError
            is SocketTimeoutException -> Failure.SocketTimeoutError
            else -> Failure.ServerError
        }.run {
            Either.Left(this)
        }
    }
}


fun <T> handleFailure(response: Response<BaseBean<T>>): Failure {
    return when {
        !response.isSuccessful -> {
            Failure.ServerError
        }
        response.body() == null -> {
            Failure.ServerError
        }
        response.body()?.errorCode != 0 -> {
            Failure.BusinessError(response.body()?.errorCode, response.body()?.errorMsg)
        }
        else -> {
            Failure.ServerError
        }
    }
}

fun failureVerdict(failure: Failure?): Int {
    when (failure) {
        is Failure.NetworkConnection -> return (R.string.failure_network_connection);
        is Failure.ServerError -> return (R.string.failure_server_error);
        is Failure.JsonSyntaxError -> return (R.string.failure_data_error);
        else -> return (R.string.failure_server_error);
    }
}
