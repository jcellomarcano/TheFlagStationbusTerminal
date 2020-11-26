package com.example.thefalgbusstop.domain

import com.example.thefalgbusstop.data.repositories.*
import com.example.thefalgbusstop.domain.entities.*
import io.reactivex.Single

class  ChofersUseCase(
        private val choferRepository: ChoferRepository
) {

    fun invoke(): Single<List<Chofer>> =
            choferRepository.getAllChofers()

    fun invoke(Id: Int): Single<Chofer> = choferRepository.getChoferRepo(Id)

    fun invokeDelete(id: Int): Single<responsePojo> = choferRepository.deleteChofer(id)

    fun invokeUpdate(chofer: ChoferUpdate, id: Int): Single<responsePojo> = choferRepository.updateChoferRepo(chofer, id)

    fun invokeCreate(chofer: ChoferPost):Single<responsePojo> = choferRepository.createChofer(chofer)
}

class GetAllBusUseCase(
        private val BusRepository: BusRepository
) {

    fun invoke(): Single<List<Bus>> =
            BusRepository.getAllBus()

//    fun invoke(Id: Int): Single<Bus> = BusRepository.getBusRepo(Id)
}
class GetAllPassengersUseCase(
        private val PassengerRepository: PassengerRepository
) {

    fun invoke(): Single<List<Passenger>> =
            PassengerRepository.getAllPassenger()

//    fun invoke(Id: Int): Single<Passenger> = PassengerRepository.getPassengerRepo(Id)
}class GetAllRoutesUseCase(
        private val RouteRepository: RouteRepository
) {

    fun invoke(): Single<List<Route>> =
            RouteRepository.getAllRoute()

//    fun invoke(Id: Int): Single<Chofer> = choferRepository.getChoferRepo(Id)
}class GetAllSitsUseCase(
        private val SitRepository: SitRepository
) {

    fun invoke(): Single<List<Sit>> =
            SitRepository.getAllSit()

//    fun invoke(Id: Int): Single<Sit> = SitRepository.getSitRepo(Id)
}class GetAllHorariosUseCase(
        private val HorariosRepository: HorariosRepository
) {

    fun invoke(): Single<List<Horarios>> =
            HorariosRepository.getAllHorarios()

//    fun invoke(Id: Int): Single<Horarios> = HorariosRepository.getHorariosRepo(Id)
}