cmake_minimum_required(VERSION 3.22)
project(cpp_pro)

set(CMAKE_CXX_STANDARD 11)

add_executable(cpp_pro
        override_operator_assign/main.cpp
        override_operator_assign/my_string.hpp
        override_operator_assign/my_string.cpp
        )
