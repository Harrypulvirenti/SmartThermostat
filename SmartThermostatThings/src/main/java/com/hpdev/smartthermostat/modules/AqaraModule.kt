package com.hpdev.smartthermostat.modules

import com.hpdev.smartthermostat.service.aqara.AqaraMessageReceiver
import com.hpdev.smartthermostat.service.aqara.AqaraMessageReceiverImpl
import com.hpdev.smartthermostat.service.aqara.AqaraMulticastService
import com.hpdev.smartthermostat.service.aqara.AqaraMulticastServiceImpl
import com.hpdev.smartthermostat.service.wrapper.UDPMessenger
import com.hpdev.smartthermostat.service.wrapper.UDPMessengerImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val aqaraModule = module {
    single { AqaraMessageReceiverImpl(get()) as AqaraMessageReceiver }

    single { AqaraMulticastServiceImpl(get(), get(), get(named(TEMPERATURE_UPDATER)), get(named(IP_UPDATER))) as AqaraMulticastService }

    single { UDPMessengerImpl(get()) as UDPMessenger }
}