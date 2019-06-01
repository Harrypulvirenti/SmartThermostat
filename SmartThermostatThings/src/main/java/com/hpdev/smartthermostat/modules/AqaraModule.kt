package com.hpdev.smartthermostat.modules

import com.hpdev.smartthermostat.network.MulticastReceiver
import com.hpdev.smartthermostat.network.MulticastReceiverImpl
import com.hpdev.smartthermostat.network.UDPMessenger
import com.hpdev.smartthermostat.network.UDPMessengerImpl
import com.hpdev.smartthermostat.service.AqaraMessageReceiver
import com.hpdev.smartthermostat.service.AqaraMessageReceiverImpl
import com.hpdev.smartthermostat.service.AqaraMulticastService
import com.hpdev.smartthermostat.service.AqaraMulticastServiceImpl
import com.hpdev.smartthermostat.service.AqaraSensorDiscoveryService
import com.hpdev.smartthermostat.service.AqaraSensorDiscoveryServiceImpl
import com.hpdev.smartthermostat.service.TemperatureSensorService
import com.hpdev.smartthermostat.service.TemperatureSensorServiceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val aqaraModule = module {

    factory<MulticastReceiver> { MulticastReceiverImpl() }

    single<AqaraMessageReceiver> {
        AqaraMessageReceiverImpl(
            get(),
            get()
        )
    }

    single<AqaraMulticastService> {
        AqaraMulticastServiceImpl(
            get(),
            get(),
            get(named(TEMPERATURE_UPDATER)),
            get(named(IP_UPDATER))
        )
    }

    factory<UDPMessenger> { UDPMessengerImpl(get()) }

    single<AqaraSensorDiscoveryService> { AqaraSensorDiscoveryServiceImpl(get(), get(), get(named(IP_SUBSCRIBER))) }

    single<TemperatureSensorService> {
        TemperatureSensorServiceImpl(
            get(),
            get(),
            get(named(TEMPERATURE_UPDATER)),
            get(named(IP_SUBSCRIBER)),
            get(named(SENSOR_SUBSCRIBER))
        )
    }
}