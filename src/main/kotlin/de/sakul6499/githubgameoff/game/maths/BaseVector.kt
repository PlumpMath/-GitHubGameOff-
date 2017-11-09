package de.sakul6499.githubgameoff.game.maths

abstract class BaseVector<Type>(val range: Int) {
    abstract fun add(other: BaseVector<*>): BaseVector<Type>
    abstract fun substract(other: BaseVector<*>): BaseVector<Type>
    abstract fun multiply(other: BaseVector<*>): BaseVector<Type>
    abstract fun divide(other: BaseVector<*>): BaseVector<Type>

    abstract fun nullify(): BaseVector<Type>
    abstract fun nullify(): BaseVector<Type>
}