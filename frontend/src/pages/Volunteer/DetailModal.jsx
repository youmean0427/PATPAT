import React from 'react';
import styles from './DetailModal.module.scss';

export default function Modal(props) {
  const { open, close, save, title } = props;
  return (
    <div className={open ? `${styles.openModal} ${styles.modal}` : styles.modal}>
      {open ? (
        <section>
          <header className={styles.title}>
            {title}
            <button className={styles.close} onClick={close}>
              &times;
            </button>
          </header>
          <main className={styles['modal-main']}>{props.children}</main>
        </section>
      ) : null}
    </div>
  );
}
