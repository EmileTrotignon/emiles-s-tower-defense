package GUI

import scala.collection._

class FSignal[T]
{
    private val callbacks: mutable.Set[T => Unit] = mutable.Set[T => Unit]()

    def add_callback(f: T => Unit): Unit =
    {
        callbacks.addOne(f)
    }

    def emit(t: T): Unit =
    {
        callbacks.foreach(f => f(t))
    }
}
