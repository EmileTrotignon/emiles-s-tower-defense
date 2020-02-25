package GUI

import scala.collection._

class FSignal[T]
{
    private def slots: mutable.Set[T => Unit] = mutable.Set[T => Unit]()

    def add_callback(f: T => Unit): Unit =
    {
        slots.addOne(f)
    }

    def emit(t: T): Unit =
    {
        slots.foreach(f => f(t))
    }
}
