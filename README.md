Battery Fuel Gauge
====================

Android library which aims to provide more accurate measurements on battery properties.  

The table below contains tested physical devices supporting battery fuel gauge measurements:

| Device                  | API | Voltage | Temperature | Capacity | CurrentNow  | CurrentAverage | ChargeCounter | EnergyCounter |
|:-----------------------:|:---:|:-------:|:-----------:|:--------:|:-----------:|:--------------:|:-------------:|:-------------:|
| Huawei Watch 2          | 25  | ✔       | ✔           | ✔        | ✔            | ✔             |               |               |
| Nexus 9                 | 21  | ✔       | ✔           | ✔        | ✔            | ✔             | ✔             | ✔             |
| Nexus 6                 | 23  | ✔       | ✔           | ✔        | ✔            | ✔             | ✔             |               |
| OnePlus One             | 23  | ✔       | ✔           | ✔        | ✔            |               |               |               |
| OnePlus X               | 23  | ✔       | ✔           | ✔        | ✔            |               |               |               |
| Google Pixel            | 25  | ✔       | ✔           | ✔        | ✔            |               | ✔             |               |

More information on battery properties:  
https://source.android.com/devices/tech/power/device.html 

Setup
====================

1. Add the JitPack repository to your build file
```Groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency
```Groovy
dependencies {
    compile 'com.github.claudiugroza:batterygauge:v0.1'
}
```

Usage
====================

1. Configure the logger and initialize the battery gauge. It should be done once, before trying to log any battery stats.
```Java
BatteryGaugeConfig config = new BatteryGaugeConfig(context);
BatteryGauge.init(config);
```

2. Log the battery stats as you're using the common logger
```Java
BatteryGauge.log();
```

3. Release the resource
```Java
BatteryGauge.destroy();
```

Licence
====================

Copyright 2017 Claudiu Groza  

Licensed under the Apache License, Version 2.0 (the “License”); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0  

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
