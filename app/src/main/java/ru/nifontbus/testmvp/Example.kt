package ru.nifontbus.testmvp

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.cast
import io.reactivex.rxjava3.kotlin.toObservable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Example {

    fun init() {
        Consumer(Producer()).execute()
    }

    /** Производитель данных */
    class Producer {

        fun getJustObservable(): Observable<String> {
            return Observable.just("1", "2", "1", "4")
        }

        fun getJust2Observable(): Observable<String> {
            return Observable.just("a", "b", "c", "d", "e")
        }

        fun getFromIterableObserver(): Observable<String> {
            return Observable.fromIterable(arrayListOf("1", "2", "3"))
        }

        fun kotlinToObservable(): Observable<String> {
            return arrayListOf("1", "2", "3").toObservable()
        }

        fun getIntervalObserver(): Observable<Long> {
            return Observable.interval(3, TimeUnit.SECONDS)
        }

        fun getTimerObserver(): Observable<Long> {
            return Observable.timer(5, TimeUnit.SECONDS)
        }

        fun getRangeObservable(): Observable<Int> {
            return Observable.range(10, 5)
        }

        fun randomResult(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun getFromCallableObservable(): Observable<Boolean> {
            return Observable.fromCallable {
                randomResult()
            }
        }

        fun getCreateObservable() = Observable.create<String> { emitter ->
            try {
                for (i in 0..10) {
                    val result = randomResult()
                    if (result) {
                        emitter.onNext("Success")
                    } else {
                        emitter.onError(IllegalStateException("Can't be false"))
                    }
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    /** Потребитель данных */
    class Consumer(val producer: Producer) {

        private val TAG = "RxJava"

        /** Выполнение «потребления» */
        fun execute() {
            val stringObserver = object : Observer<String> {
                var disposable: Disposable? = null

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                    disposable = d
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }
            }

            // val observable = producer.getJust2Observable()
            val observable2 = producer.getJustObservable()

            observable2
                .cast<String>()
                .subscribe(stringObserver)
        }
    }

}