package com.skillbox.basicsyntax

/*
Что нужно сделать
Создайте переменную firstName. Она должна иметь тип String. Переменная должна ссылаться на строку с вашим именем.
Создайте переменную lastName. Она должна иметь тип String. Переменная должна ссылаться на строку с вашей фамилией.
Создайте переменную height. Она должна иметь тип Int. Переменная должна хранить ваш рост, указанный в см. Переменная должна быть изменяемой.
Создайте переменную weight. Она должна иметь тип Float. Переменная должна хранить ваш вес, указанный в кг.
Создайте переменную isChild. Она должна иметь тип Boolean. Переменная должна хранить значение, являетесь ли вы ребёнком.
 Значение переменной должно вычисляться исходя из значений переменных height, weight. Считайте, что человек является ребенком ЕСЛИ рост МЕНЬШЕ 150 см ИЛИ вес МЕНЬШЕ 40 кг.
Создайте переменную info, которая будет ссылаться на строку с информацией о вас. В строке должна быть вся информация, доступная из переменных: firstName, lastName, height, weight, isChild. Используйте для этого строковые шаблоны.
Выведите значение переменной info в консоль.
Измените значение переменной height и выведите в консоль обновлённую информацию.*/

fun main() {
    val firstName = "Sergei"
    val lastName = "Pavlyuchenko"
    var height = 191
    val weight = 105f
    var isChild = height < 150 || weight < 40
    var info  = "My name is $firstName $lastName, my height is $height cm and weight is $weight kg. Im ${if (isChild) "a child." else "not child."}"
    println(info)
    height = 149
    isChild = height < 150 || weight < 40
    info = "My name is $firstName $lastName, my height is $height cm and weight is $weight kg. Im ${if (isChild) "a child." else "not child."}"
    print(info)
}