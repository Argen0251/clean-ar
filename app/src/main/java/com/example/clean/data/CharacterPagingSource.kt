package com.example.clean.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.clean.data.datasource.CartoonApiService
import com.example.clean.data.model.CharacterResponse

private const val START_PAGE = 1

class CharacterPagingSource(
    private val api: CartoonApiService
) : PagingSource<Int, CharacterResponse.Result>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse.Result>): Int? =
        state.anchorPosition
            ?.let { pos -> state.closestPageToPosition(pos) }
            ?.prevKey
            ?.plus(1)
            ?: state.closestPageToPosition(state.anchorPosition ?: 0)
                ?.nextKey
                ?.minus(1)


    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, CharacterResponse.Result> {
        return try {
            val page = params.key ?: START_PAGE
            val response = api.getCharactersList(page)
            val list = response.results

            val nextKey = if (response.info?.next != null) page + 1 else null
            val prevKey = if (page == START_PAGE) null else page - 1

            LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}