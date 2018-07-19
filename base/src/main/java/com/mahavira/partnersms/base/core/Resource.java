package com.mahavira.partnersms.base.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.mahavira.partnersms.base.core.Status.ERROR;
import static com.mahavira.partnersms.base.core.Status.LOADING;
import static com.mahavira.partnersms.base.core.Status.SUCCESS;

/**
 * The wrapper class of data, contains current state of the data including {@link #status},
 * {@link #message} and {@link #code}
 *
 */

public class Resource<T> {

    /**
     * Contains the current status of data.
     * Please refer to the enum {@link Status}
     */
    @NonNull
    public final Status status;

    /**
     * Additional information message regarding to state of data
     * Usually used in case of data retrieving was failed
     */
    @Nullable
    public final String message;

    /**
     * Contains the integer code reflecting to the state of data
     */
    @Nullable
    public final Integer code;

    /**
     * Contains the wrapped data
     */
    @Nullable
    public final T data;

    public Resource(@NonNull Status status, @Nullable T data,
                    @Nullable Integer code, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null, null);
    }

    public static <T> Resource<T> error(Integer code, String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, code, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        if (code != null ? !code.equals(resource.code) : resource.code != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
