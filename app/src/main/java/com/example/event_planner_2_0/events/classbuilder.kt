package com.example.event_planner_2_0.events

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass


// Creates Classes (Which go inside the Classes_Built) based on the Event Class (Child classes)
// Author: Kelson

class ClassBuilder() {
    private val classes = mutableMapOf<String, KClass<out Event>>()

    data class Quintuple<A,B,C,D,E>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D,
        val fifth: E
    )

    var date = LocalDate.now()
    var time = LocalTime.now()
    

    fun createClass(className: String, attributes: Map<String, Any>): KClass<out Event> {
        val newClass = object : Event("Event","Test","Hello", date, time, "Test", listOf()) {
            init {
                attributes.forEach { (key, value) ->
                    println("$key: $value")
                    }
                }
            }
            classes[className] = newClass::class
            return newClass::class
        }

    fun getClass(className: String): KClass<out Event>? {
        return classes[className]
    }
}

fun main() {
    val builder = ClassBuilder()

    print("Enter the name of the new class: ")
    val className = readLine() ?: "DefaultClass"
    
    print("Enter an attribute name: ")
    val attributeName = readLine() ?: "defaultAttribute"
    
    print("Enter an attribute value: ")
    val attributeValue = readLine() ?: "defaultValue"

    val newClass = builder.createClass(className, mapOf(attributeName to attributeValue))

}

