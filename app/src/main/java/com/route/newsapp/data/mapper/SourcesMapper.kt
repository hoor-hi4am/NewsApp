package com.route.newsapp.data.mapper

import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.domain.model.Source
import javax.inject.Inject

class SourcesMapper @Inject constructor(){
    //يحول الحاجة الي جاية من الباك اند للحاجة الي عايزها الكومبوزبل

    fun toSource(sourceDM: SourceDM): Source {
        return Source(
            name = sourceDM.name,
            id = sourceDM.id
        )
    }

    //بنوريله ازاي يحول ليستة و الماب بتعمل كده
    fun toSources(sources: List<SourceDM>): List<Source> {
        return sources.map { sourceDM ->
            return@map Source(
                name = sourceDM.name,
                id = sourceDM.id
            )
        }
    }
}