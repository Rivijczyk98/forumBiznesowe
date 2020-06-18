import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../_services/category.service';
import {Category} from '../_model/category';
import {MatDialog} from '@angular/material/dialog';
import {CategoryAddComponent} from '../category-add/category-add.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  categories: Category[] = [];

  constructor(
    private categoryService: CategoryService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.refreshData();
  }

  createCategory() {
    const dialogRef = this.dialog.open(CategoryAddComponent, {
      hasBackdrop: true,
      // data: this.bugDescription
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.doAction === true) {
        this.categoryService.addCategory(this.prepareCategoryRequest(result));
      }
    });
  }

  private refreshData() {
    this.categoryService.findAll().subscribe(data => {
      this.categories = data;
    });
  }

  private prepareCategoryRequest(result: any): Category {
    return {
      name: result.categoryName,
      description: result.categoryDescription
    };
  }
}
