import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeviceComponent } from './device/device.component';

const routes: Routes = [
  { path: '',       redirectTo: 'device', pathMatch: 'full' }, // default route
  { path: 'device', component: DeviceComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
