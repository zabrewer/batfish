load("@rules_java//java:defs.bzl", "java_library")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "palo_alto_base",
    srcs = [
        ":PaloAltoBaseLexer.java",
    ],
    deps = [
        "//projects/batfish-common-protocol:parser_common",
        "@maven//:com_google_code_findbugs_jsr305",
        "@maven//:org_antlr_antlr4_runtime",
    ],
)
