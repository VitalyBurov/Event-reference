package by.burov.classifiers.core.dto;

import javax.validation.constraints.NotBlank;

public class CreateConcertCategoryDto {

        @NotBlank
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
}
