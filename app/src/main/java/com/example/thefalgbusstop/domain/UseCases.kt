package com.example.thefalgbusstop.domain

import com.example.thefalgbusstop.data.repositories.ChoferRepository
import io.reactivex.Single

class GetAllChofersUseCase(
        private val choferRepository: ChoferRepository
) {

    fun invoke(): Single<List<Chofer>> =
            choferRepository.getAllChofers()

    fun invoke(Id: Int): Single<Chofer> = choferRepository.getChofer(Id)
}