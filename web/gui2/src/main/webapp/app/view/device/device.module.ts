/*
 * Copyright 2015-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeviceComponent } from './device.component';
import { DeviceDetailsPanelDirective } from './devicedetailspanel.directive';
import { RemoteModule } from '../../fw/remote/remote.module';
/**
 * ONOS GUI -- Device View Module
 */
@NgModule({
  exports: [
    DeviceComponent
  ],
  imports: [
    CommonModule,
    RemoteModule
  ],
  declarations: [
    DeviceComponent,
    DeviceDetailsPanelDirective
  ]
})
export class DeviceModule { }
