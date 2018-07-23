package com.mahavira.partner.products.domain.repo;

import com.mahavira.partner.products.domain.entity.Boardgame;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 23/07/18.
 */

public interface ProductRepository {
    Single<List<Boardgame>> getLent();
    Completable returnLent(Boardgame product);
}
