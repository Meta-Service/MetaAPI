# MetaAPI
MetaAPI Is a free, lightweight api that makes coding much easier. 
It contains all base features from color utils to basecommand and arguments. It even contains utils for database creation or if you want to keep it simple, json storage.

# Using MetaAPI

### Maven
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```xml
	<dependency>
	    <groupId>com.github.Meta-Service</groupId>
	    <artifactId>MetaAPI</artifactId>
	    <version>V1.0.2</version>
	</dependency>
```

### Gradle
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

```
	dependencies {
	        implementation 'com.github.Meta-Service:MetaAPI:V1.0.2'
	}
```
