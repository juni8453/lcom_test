<template>
    <v-container>
        <v-card class="pa-3 ">
             <v-list
                :shaped="shaped"
                :subheader="subheader"
            >
        <v-subheader>게시판</v-subheader>
        <v-list-item-group v-model="item" color="primary">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>글 제목</v-list-item-title>
              <v-list-item-subtitle> 글 내용</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>글 제목1</v-list-item-title>
              <v-list-item-subtitle > 글 내용11</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
           <v-list-item>
            <v-list-item-content>
              <v-list-item-title>글 제목2</v-list-item-title>
              <v-list-item-subtitle > 글 내용2</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
           <v-list-item>
            <v-list-item-content>
              <v-list-item-title>글 제목3</v-list-item-title>
              <v-list-item-subtitle > 글 내용3</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
           <v-list-item>
            <v-list-item-content>
              <v-list-item-title>글 제목4</v-list-item-title>
              <v-list-item-subtitle > 글 내용4</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
           <v-list-item>
            <v-list-item-content>
              <v-list-item-title>글 제목5</v-list-item-title>
              <v-list-item-subtitle > 글 내용5</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          
        </v-list-item-group>
      </v-list>
        </v-card>
    </v-container>
</template>
