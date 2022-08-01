;;   Copyright (c) Dragan Djuric. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) or later
;;   which can be found in the file LICENSE at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(defproject uncomplicate/neanderthal "0.45.0"
  :description "Neanderthal is a Clojure library for fast matrix and linear algebra computations."
  :url "https://github.com/uncomplicate/neanderthal"
  :scm {:name "git"
        :url "https://github.com/uncomplicate/neanderthal"}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [uncomplicate/commons "0.13.0"]
                 [uncomplicate/fluokitten "0.9.1"]
                 [uncomplicate/neanderthal-native "0.33.0"]
                 [uncomplicate/clojurecl "0.15.1"]
                 [org.jocl/jocl-blast "1.5.2"]
                 [uncomplicate/clojurecuda "0.16.0"]
                 [org.jcuda/jcublas "11.7.0"]
                 [org.apache.commons/commons-math3 "3.6.1"]]

  :profiles {:dev {:plugins [[lein-midje "3.2.1"]
                             [lein-codox "0.10.7"]]
                   :global-vars {*warn-on-reflection* true
                                 *assert* false
                                 *unchecked-math* :warn-on-boxed
                                 *print-length* 128}
                   :dependencies [[midje "1.10.5"]
                                  [codox-theme-rdash "0.1.2"]]
                   :codox {:metadata {:doc/format :markdown}
                           :source-uri "http://github.com/uncomplicate/neanderthal/blob/master/{filepath}#L{line}"
                           :themes [:rdash]
                           :namespaces [uncomplicate.neanderthal.core
                                        uncomplicate.neanderthal.linalg
                                        uncomplicate.neanderthal.native
                                        uncomplicate.neanderthal.opencl
                                        uncomplicate.neanderthal.cuda
                                        uncomplicate.neanderthal.math
                                        uncomplicate.neanderthal.vect-math
                                        uncomplicate.neanderthal.real
                                        uncomplicate.neanderthal.auxil
                                        uncomplicate.neanderthal.random]
                           :output-path "docs/codox"}

                   ;;also replaces lein's default JVM argument TieredStopAtLevel=1
                   ;; If building on Java 9+, uncomment the --add-opens=etc. line.
                   :jvm-opts ^:replace ["-Dclojure.compiler.direct-linking=true"
                                        "-XX:MaxDirectMemorySize=16g" "-XX:+UseLargePages"
                                        "--add-opens=java.base/jdk.internal.ref=ALL-UNNAMED"
                                        "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED"]}}

  :javac-options ["-target" "1.8" "-source" "1.8" "-Xlint:-options"]
  :source-paths ["src/clojure" "src/device"]
  :java-source-paths ["src/java"]
  :test-paths ["test"])
