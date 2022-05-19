cmake_minimum_required(VERSION 3.22)
project(cpp_pro)

set(CMAKE_CXX_STANDARD 11)

add_executable(cpp_pro
        main.cpp
        override_operator_assign/my_string.hpp
        override_operator_assign/my_string.cpp
        linked_list/linked_list.hpp
        linked_list/linked_list.cpp
        array/array.hpp
        array/array_practice.cpp
        stack/my_stack.hpp
        stack/my_stack.cpp
        queue/my_queue.hpp
        queue/my_queue.cpp
        recursion/my_recursion.hpp
        recursion/my_recursion.cpp
        tree/binary_tree.hpp
        tree/binary_tree.cpp
        )
