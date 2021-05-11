package scripts

plugins { id("com.android.application")  }

private object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

object Default {
    const val BUILD_TYPE = BuildTypes.DEBUG
}

android {
    buildTypes {
        maybeCreate(BuildTypes.DEBUG).apply {
            isMinifyEnabled = false
        }

        maybeCreate(BuildTypes.RELEASE).apply {
            isMinifyEnabled = false
        }
    }
}