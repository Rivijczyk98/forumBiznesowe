import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'postSummary'
})
export class PostSummaryPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): string {
    let str: string = "";

    if(value.length > 250){
      for(let i = 0; i < 247; i++){
        str += value[i];
      }
      str += "..."

      return str
    } else {
      return value;
    }
    
  }
}
