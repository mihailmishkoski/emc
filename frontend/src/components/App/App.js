import {BrowserRouter as Router, Route} from 'react-router-dom';
import './App.css';
import React, {Component} from "react";
import Books from "../Books/BookList/books";
import EshopService from "../../repository/eshop.Repository";
import Authors from "../Author/AuthorList/authors";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Header from "../Header/header";
import Categories from "../Categories/CategoriesList/categories";





class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>

                    <div className={"container"}>
                        <br/>

                        <Route path={"/categories"} exact render={() =>
                            <Categories categories={this.state.categories}/>}/>

                        <Route path={"/categories"}
                               categories={this.state.categories}/>
                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}
                                   onDelete={this.deleteBook}
                                   onOutOfStock={this.outOfStock}
                                   onEdit={this.getBook}
                                   onSearchBooks={this.searchBook}/>}/>

                        <Route path={"/authors"} exact render={() =>
                            <Authors authors={this.state.authors}/>}/>


                        <Route path={"/books/add"} exact render={() =>
                            <BookAdd books={this.state.books}
                                     authors={this.state.authors}
                                     categories={this.state.categories}
                                     onAddBook={this.addBook}/>}/>


                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit book={this.state.selectedBook}
                                      authors={this.state.authors}
                                      categories={this.state.categories}
                                      onEditBook={this.editBook}
                            />}/>
                    </div>
                </main>
            </Router>
        );
    }


    searchBook = (name) => {
        EshopService.getBookByName(name)
            .then((data) => {
                this.setState({
                    books: data.data
                })
            })
    }
    loadBooks = () => {
        EshopService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }
    loadAuthors = () => {
        EshopService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }
    loadCategories = () => {
        EshopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }
    addBook = (bookName, bookCategory, authorId, availableCopies) => {
        EshopService.addBook(bookName, bookCategory, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    deleteBook = (id) => {
        EshopService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }
    getBook = (id) => {
        EshopService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
    outOfStock = (id) =>{
        EshopService.markOutOfStock(id)
            .then((data) => {
               this.loadBooks();
            })
    }
    editBook = (id, bookName, bookCategory, authorId, availableCopies) =>{
        EshopService.editBook(id, bookName, bookCategory, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    componentDidMount() {
        this.loadBooks();
        this.loadAuthors()
        this.loadCategories()
    }
}
export default App;