import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.*

object RepoConfig {
    const val group = "com.study.view"
    const val version = "1.0"
    const val artifactId = "banner"
}

apply(plugin = "maven-publish")
configure<PublishingExtension> {
    repositories {
        mavenLocal()
    }
}
afterEvaluate {
    extensions.configure<PublishingExtension>("publishing") {
        publications {
            create<MavenPublication>("release") { //对应release 版 build variant
                groupId = RepoConfig.group
                artifactId = RepoConfig.artifactId
                version = RepoConfig.version

                from(components["release"])
            }
        }
    }
}