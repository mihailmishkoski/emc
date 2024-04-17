import React from 'react';
import { useHistory } from 'react-router-dom';

const BookEdit = (props) => {
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        bookName: "",
        bookCategory: 0,
        authorId: 0,
        availableCopies: 0
    });

    // Populate form data with book information when the component mounts
    React.useEffect(() => {
        if (props.book) {
            updateFormData({
                bookName: props.book.bookName || "",
                bookCategory: props.book.bookCategory || 0,
                authorId: props.book.authorId || 0,
                availableCopies: props.book.availableCopies || 0
            });
        }
    }, [props.book]);

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        });
    };

    const onFormSubmit = (e) => {
        e.preventDefault();
        const { bookName, bookCategory, authorId, availableCopies } = formData;

        props.onEditBook(props.book.id, bookName, bookCategory, authorId, availableCopies);
        history.push("/books");
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="bookName">Book name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="bookName"
                            name="bookName"
                            value={formData.bookName}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="bookCategory">Category</label>
                        <select
                            className="form-control"
                            id="bookCategory"
                            name="bookCategory"
                            value={formData.bookCategory}
                            onChange={handleChange}
                        >
                            {props.categories.map((category) => (
                                <option key={category.id} value={category.id}>{category}</option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="authorId">Author</label>
                        <select
                            className="form-control"
                            name="authorId"
                            value={formData.authorId}
                            onChange={handleChange}
                        >
                            {props.authors.map((author) => (
                                <option key={author.id} value={author.id}>{author.authorName}</option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input
                            type="number"
                            className="form-control"
                            id="availableCopies"
                            name="availableCopies"
                            value={formData.availableCopies}
                            onChange={handleChange}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary">Save Changes</button>
                </form>
            </div>
        </div>
    );
};

export default BookEdit;
