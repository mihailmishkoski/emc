import React from 'react'
import { useHistory } from "react-router-dom";

const BookAdd = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        bookName: "",
        bookCategory: 0,
        authorId: 0,
        availableCopies: 0
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        });
    };


    const onFormSubmit = (e) => {
        e.preventDefault();
        const { bookName, bookCategory, authorId, availableCopies  } = formData;
        props.onAddBook(bookName, bookCategory, authorId, availableCopies);
        history.push("/books");
    };

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="bookName">Book Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="bookName"
                            name="bookName"
                            required
                            placeholder="Enter book name"
                            onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="bookCategory">Category</label>
                        <select className="form-control" id="bookCategory" name="bookCategory" required
                                onChange={handleChange}>
                            <option >Select Category</option>
                            {props.categories.map((term) =>
                                <option value={term.id}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="authorId">Author</label>
                        <select className="form-control" id="authorId" name="authorId" required onChange={handleChange}>
                            <option value="">Select Author</option>
                            {props.authors.map((term) =>
                                <option value={term.id}>{term.authorName}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input
                            type="number"
                            className="form-control"
                            id="availableCopies"
                            name="availableCopies"
                            required
                            placeholder="Enter available copies"
                            onChange={handleChange}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary">Add Book</button>
                </form>
            </div>
        </div>
    );

};

export default BookAdd;
