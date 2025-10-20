package com.example.mylittlepony.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PonyPagingSource(
    private val repository: Repository,
) : PagingSource<Int, PonyData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PonyData> {
        val page = params.key ?: 1
        return try {
            val response = repository.getPonyData(page)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PonyData>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}