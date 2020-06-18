import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Component, Inject} from '@angular/core';
import {Category} from '../_model/category';
import {CategoryService} from '../_services/category.service';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent {

  form: any = {};
  errorMessage = '';

  constructor(
    private categoryService: CategoryService
  ) {
  }

  onSubmit() {
    this.categoryService.addCategory(this.prepareCategoryRequest()).subscribe(data => {
      console.log('guguGU');
    });
  }

  prepareCategoryRequest(): Category {
    return {
      name: this.form.categoryName,
      description: this.form.categoryDescription
    };
  }

}
