import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../_services/category.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Category} from '../_model/category';

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.css']
})
export class CategoryEditComponent implements OnInit {

  editedCategory: Category = new Category();

  constructor(
    private categoryService: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.editedCategory.id = +params.get('id');
    });

    this.categoryService.findById(this.editedCategory.id).subscribe(data => {
      this.editedCategory = data;
    });

  }

  onSubmit() {
    this.categoryService.updateCategory(this.prepareCategoryRequest()).subscribe(() => {
      this.router.navigate(['/categories']);
    });
  }

  prepareCategoryRequest(): any {
    return {
      id: this.editedCategory.id,
      name: this.editedCategory.name,
      description: this.editedCategory.description
    };
  }

}
