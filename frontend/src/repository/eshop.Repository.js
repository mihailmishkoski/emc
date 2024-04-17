import axios from '../custom-axios/axios';

const EshopService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    fetchCategories: () => {
        return axios.get("/books/categories")
    },
    addBook: (bookName, bookCategory, authorId, availableCopies) => {
        return axios.post("/books/add", {
            "name": bookName,
            "category": bookCategory,
            "author": authorId,
            "availableCopies": availableCopies,
            "isAvailable" : availableCopies>0
        });
    },
    editBook: (id, bookName, bookCategory, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": bookName,
            "category": bookCategory,
            "author": authorId,
            "availableCopies": availableCopies,
            "isAvailable" : availableCopies>0
        });
    },
    getBook:(id) => {
        return axios.get(`/books/${id}`)
    },
    getBookByName:(name) => {
        return axios.get(`/books/getByName/${name}`)
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    markOutOfStock: (id) => {
        return axios.post(`books/outOfStock/${id}`)
    }

}
export default EshopService;