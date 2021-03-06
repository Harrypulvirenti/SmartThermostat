package extensions

import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<BuildType>.debug(
    configureClosure: Action<BuildType>
) {
    getByName("debug", configureClosure)
}

fun NamedDomainObjectContainer<BuildType>.release(
    configureClosure: Action<BuildType>
) {
    getByName("release", configureClosure)
}