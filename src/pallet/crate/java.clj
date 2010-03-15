(ns pallet.crate.java
  (:use [pallet.resource.package :only [package package-manager]]))

(def package-names
     {:openjdk "openjdk-6-"
      :sun "sun-java6-"})

(defn java
  "Install java.  Options can be :sun, :openjdk, :jdk, :jre.
By default sun jdk will be installed."
  [& options]
  (let [implementations (filter (set (keys package-names)) options)
        components (filter #{:jdk :jre} options)]
    (package-manager :universe)
    (package-manager :multiverse)
    (package-manager :update)
    (doseq [implementation (or (seq implementations) [:sun])
            component (or (seq components) [:jdk])]
      (package (str (package-names implementation) (name component))))))
