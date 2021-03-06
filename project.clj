(defproject io.github.technical27/afk "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :aot :all
  :target-path "target/%s"
  :repositories [["spigot-repo" "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"]
                 ["placeholderapi" "https://repo.extendedclip.com/content/repositories/placeholderapi/"]
                 ["gh" "https://maven.pkg.github.com/Technical27/afk"]]
  :java-source-paths ["java"]
  :profiles {:provided {:dependencies [[org.spigotmc/spigot-api "1.17-R0.1-SNAPSHOT" :scope "runtime"]
                                       [me.clip/placeholderapi "2.10.9" :scope "runtime"]]}
             :uberjar {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
