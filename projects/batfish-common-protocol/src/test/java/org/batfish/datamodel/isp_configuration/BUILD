load("@batfish//skylark:junit.bzl", "junit_tests")

package(
    default_testonly = True,
    default_visibility = ["//visibility:private"],
)

junit_tests(
    name = "tests",
    srcs = glob(["*Test.java"]),
    resources = [
        "//projects/batfish-common-protocol/src/test/resources/org/batfish/datamodel/isp_configuration",
    ],
    deps = [
        "//projects/batfish-common-protocol:common",
        "@maven//:com_fasterxml_jackson_core_jackson_core",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_guava_guava_testlib",
        "@maven//:junit_junit",
        "@maven//:org_hamcrest_hamcrest",
    ],
)
