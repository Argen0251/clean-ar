package com.example.clean.di.data

import com.example.clean.BuildConfig
import com.example.clean.data.datasource.CartoonApiService
import com.example.clean.data.repository.CharacterRepositoryImpl
import com.example.clean.domain.repasitory.CharacterRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<Interceptor> { provideHttpLoggingInterceptor() }

    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single<CartoonApiService> { provideAppService(get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(api = get()) }
}
fun provideRetrofit(
    okHttpClient: OkHttpClient
):Retrofit{
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
fun provideOkHttpClient(
    interceptor: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .readTimeout(timeout = 30, unit = TimeUnit.SECONDS)
        .connectTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        .addInterceptor  (interceptor)
        .build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.BODY
    }
}
fun provideAppService(
    retrofit: Retrofit
):CartoonApiService{
    return retrofit.create(CartoonApiService::class.java)
}


