import {Component} from '@angular/core';
import {Category} from '../_model/category';
import {CategoryService} from '../_services/category.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent {

  form: any = {};
  errorMessage = '';

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {
  }

  onSubmit() {
    this.categoryService.addCategory(this.prepareCategoryRequest()).subscribe(() => {
      this.router.navigate(['/categories']);
    });
  }

  prepareCategoryRequest(): Category {
    return {
      name: this.form.categoryName,
      description: this.form.categoryDescription
    };
  }

}
