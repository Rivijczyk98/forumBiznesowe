import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Component, Inject} from '@angular/core';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent {
  doAction: boolean;
  categoryName: string;
  categoryDescription: string;

  constructor(
    public dialogRef: MatDialogRef<CategoryAddComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string) {
  }

  onClick(doAction): void {
    this.doAction = doAction;
    this.dialogRef.close({
      doSend: this.doAction,
      categoryName: this.categoryName,
      categoryDescription: this.categoryDescription
    });
  }

}
