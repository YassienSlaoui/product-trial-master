import { NgModule } from '@angular/core';
import { StarRatingModule } from 'angular-star-rating';

@NgModule({
  imports: [StarRatingModule.forRoot()],
  exports: [StarRatingModule],
})
export class SharedModule {}