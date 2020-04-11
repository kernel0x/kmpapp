package com.kernel.kmpproject.data.repository

import com.kernel.kmpproject.data.source.network.NetworkSource

abstract class AppBaseRepository(protected val networkSource: NetworkSource) : BaseRepository