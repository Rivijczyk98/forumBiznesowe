import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'postedDate'
})
export class PostedDatePipe implements PipeTransform {

  transform(value: Date, ...args: unknown[]): string {
    let now: Date = new Date();

    let ms: number; 
    ms = now.getTime() - value.getTime();

    if(ms < 60000){
      return "Just now"
    } else if (ms < 60 * 60000) {
      return (Math.floor(ms / 60000)).toString() + " minutes ago"
    } else if (ms < 24 * 60 * 60000){
      return (Math.floor(ms / (60000 * 60))).toString() + " hours ago"
    }else {
      return value.getHours() + ":" + (value.getMinutes() % 60) + " | " + value.getDate() + "." + value.getMonth() + "." + value.getFullYear();
    }
  }

}
