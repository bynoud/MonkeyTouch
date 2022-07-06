package me.ductran.monkeytouch.models

import android.os.Parcel
import android.os.Parcelable

open class SocketMessage(val type: Int): Parcelable {

    companion object {
        const val MSG_PING = 1;
        const val MSG_KEY = 2;
        const val MSG_MOTION = 3;

        @JvmField
        val CREATOR = object : Parcelable.Creator<SocketMessage?> {
            override fun createFromParcel(p: Parcel): SocketMessage? {
                val type = p.readInt()
                return when (type) {
                    MSG_KEY -> KeyMessage.fromParcel(p)
                    MSG_MOTION -> MotionMessage.fromParcel(p)
                    else -> PingMessage.fromParcel(p)
                }
            }

            override fun newArray(size: Int): Array<SocketMessage?> {
                TODO("Not yet implemented")
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeInt(type)
    }

    class PingMessage(val msg: String): SocketMessage(MSG_PING) {
        companion object {
            fun fromParcel(p: Parcel) = PingMessage(p.readString()!!)
        }
        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(msg)
        }
    }

    class KeyMessage(val action: Int, val keyCode: Int): SocketMessage(MSG_KEY) {
        companion object {
            fun fromParcel(p: Parcel) = KeyMessage(p.readInt(), p.readInt())
        }
        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(action)
            out.writeInt(keyCode)
        }
    }

    class MotionMessage(val action: Int, val axis: Int, val x: Float, val y: Float): SocketMessage(MSG_KEY) {
        companion object {
            fun fromParcel(p: Parcel) = MotionMessage(p.readInt(), p.readInt(), p.readFloat(), p.readFloat())
        }
        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(action)
            out.writeInt(axis)
            out.writeFloat(x)
            out.writeFloat(y)
        }
    }
}








