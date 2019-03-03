package com.hpdev.smartthermostat.modules

import com.hpdev.smartthermostat.database.repository.AqaraMessageRepository
import com.hpdev.smartthermostat.database.repository.AqaraMessageRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {

    single { AqaraMessageRepositoryImpl(get()) as AqaraMessageRepository }
}